package com.example.athena.Firebase;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.athena.Models.Event;
import com.example.athena.Models.User;
import com.example.athena.WaitList.UserInviteArrayAdapter;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class handles database operations for the users collection in Firestore.
 */
public class UserDB {
    private static FirebaseFirestore db;
    private CollectionReference usersCollection;

    public UserDB() {
        // Initialize Firestore and set the users collection
        this.db = FirebaseFirestore.getInstance();
        this.usersCollection = db.collection("Users");
    }

    public Task<QuerySnapshot> getUserEvents(String deviceID) {
        Task ueRef = db.collection("Users/" + deviceID + "/Events").get();
        return ueRef;
    }

    public void deleteOrgFacility(String deviceID) {
         db.collection("Users").document(deviceID).update("facility", FieldValue.delete());

    }

    public void saveNotifSetting (String userID, String setting, Boolean isChecked){
        db.collection("Users").document(userID).update(setting,isChecked);
    }

    public void saveUserDetail(User user,String userID){
        db.collection("Users").document(userID).update("name",user.getName());
        db.collection("Users").document(userID).update("email",user.getEmail());
        db.collection("Users").document(userID).update("phone",user.getPhone());
    }

    public Task<QuerySnapshot> getUserList() {
        return usersCollection.get();
    }

    public static Task updateOrgEvents(String deviceID, String eventID) {
        return db.collection("Users/" + deviceID + "/OrgEvents").document(eventID).set(new HashMap<>() {});
    }

    public void updateOrgFacilities(String deviceID, String facilityID) {
        db.collection("Users").document(deviceID).update("facility", facilityID);
    }

    /**
     * removes a event from the users events subcollection
     * @param deviceID user id to look at
     * @param eventID the eventID to remove
     */
    public void leaveEvent(String deviceID, String eventID){
        db.collection("Users/" + deviceID +"/Events").document(eventID).delete();
    }

    /**
     * this function adds the eventID to users collection and sets its status to pending
     * @param deviceID userID of the where to look in the database
     * @param eventID eventID to add to the users sub collection
     */
    public void joinEvent(String deviceID,String eventID){
        Map<String,Object> status = new HashMap<>();
        status.put("status","pending");
        status.put("isNotified", true);
        usersCollection.document(deviceID).collection("Events").document(eventID).set(status);
    }

    public void resetEventNotifiedStatus(String deviceID,String eventID) {
        db.collection("Users/"+ deviceID +"/Events").document(eventID).update("isNotified",false);
    }

    /**
     * updates the users status to invited on the users events sub-collection
     * @param eventID the events id
     * @param deviceID the users id
     */
    public void changeEventStatusInvited(String eventID, String deviceID){
        db.collection("Users/"+ deviceID +"/Events").document(eventID).update("status","invited");
    }

    public void deleteUser(String userID){
        usersCollection.document(userID).delete();
    }

    /**
     * updates the users status to accepted on the users events sub-collection
     * @param eventID the events id
     * @param deviceID the users id
     */
    public void changeEventStatusAccepted(String eventID, String deviceID){
        db.collection("Users/"+ deviceID +"/Events").document(eventID).update("status","accepted");
    }

    /**
     * updates the users status to declined on the users events sub-collection
     * @param eventID the events id
     * @param deviceID the users id
     */
    public void changeEventStatusDeclined(String eventID, String deviceID){
        db.collection("Users/"+ deviceID +"/Events").document(eventID).update("status","declined");
    }

    public Task<QuerySnapshot> getOrganizerEvent(String deviceID){
        Task ueRef = db.collection("Users/" + deviceID + "/OrgEvents").get();
        return ueRef;
    }
    public Task<Void> saveURLToUser(UploadTask uploadTask, String deviceID) {
        TaskCompletionSource<Void> changeURLSource = new TaskCompletionSource<>();
        Task<Void> changeTask = changeURLSource.getTask();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference imageRef = storage.getReference().child("images/" + deviceID);
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                return imageRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Uri URL = task.getResult();
                Map<String, Object> data = new HashMap<>();
                data.put("imageURL", URL.toString());
                usersCollection.document(deviceID).set(data, SetOptions.merge());
                changeURLSource.setResult(null);
            }
        });
        return changeTask;
    }

    // Retrieves a specific user by their ID
    public Task<DocumentSnapshot> getUser(String userID) {
        return usersCollection.document(userID).get();
    }

    public void loadUserData(User user, String userID) {
        Task getUserTask = this.getUser(userID);
        getUserTask.addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                DocumentSnapshot document = (DocumentSnapshot) task.getResult();
                if (task.isSuccessful()) {
                    user.setName(document.getString("name"));
                    user.setEmail(document.getString("email"));
                    user.setPhone(document.getString("phone"));
                    user.setFacility(document.getString("facility"));
                    user.setImageURL(document.getString("imageURL"));
                } else {
                    Exception exception = task.getException();
                }
            }
        });
    }

    /**
     * this function loads event data from the database and creates a new event object to populates a list
     * the events load are events the user is invited to
     * @param deviceID user's deviceID
     * @param list arrayAdapter for list of event that the user is invited to
     */
    public void getUserInvitedEvents(String deviceID, UserInviteArrayAdapter list){
        //query for the users Events
        Task getInvited = this.getUserEvents(deviceID);
        getInvited.addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                List<String> userEventList = new ArrayList<>();

                QuerySnapshot userEvents = (QuerySnapshot) task.getResult();
                // when the query is finished add only the event where the user is invited
                if(task.isSuccessful()){
                    for (DocumentSnapshot event: userEvents.getDocuments()) {
                        if(event.getString("status").equals("invited")){
                            userEventList.add(event.getId());
                        }
                    }

                    for(String eventID: userEventList){
                        //query for the events where the user is invited
                        Task getEvents = db.collection("Events").document(eventID).get();
                        Task getInvited = db.collection("Events").document(eventID).collection("invited").get();
                        Task getPending = db.collection("Events").document(eventID).collection("invited").get();
                        Task getAccepted = db.collection("Events").document(eventID).collection("invited").get();
                        Task getDeclined = db.collection("Events").document(eventID).collection("invited").get();
                        Task gotInfo = Tasks.whenAllComplete(getEvents,getDeclined,getAccepted,getPending,getInvited);
                        gotInfo.addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            //when found add it to the array adapter
                            public void onComplete(@NonNull Task task) {
                                DocumentSnapshot document = (DocumentSnapshot) getEvents.getResult();
                                QuerySnapshot invited = (QuerySnapshot) getInvited.getResult();
                                QuerySnapshot pending = (QuerySnapshot) getPending.getResult();
                                QuerySnapshot accept = (QuerySnapshot) getAccepted.getResult();
                                QuerySnapshot decline = (QuerySnapshot) getDeclined.getResult();
                                if (task.isSuccessful()){
                                    Event event = new Event();
                                    event.setEventName(document.getString("eventName"));
                                    event.setImageURL(document.getString("imageURL"));
                                    event.setEventDescription(document.getString("eventDescription"));
                                    event.setOrganizer(document.getString("organizer"));
                                    event.setFacility(document.getString("Facility"));
                                    event.setMaxParticipants(Math.toIntExact((Long) document.get("maxParticipants")));
                                    event.setGeoRequire(document.getBoolean("geoRequire"));
                                    event.setEventID(document.getString("eventID"));


                                    List<DocumentSnapshot> inviteList = invited.getDocuments();
                                    for (DocumentSnapshot doc: inviteList) {
                                        event.getWaitList().addInvited(doc.getId(),event.getEventID());
                                    }

                                    List<DocumentSnapshot> acceptList = accept.getDocuments();
                                    for (DocumentSnapshot doc: acceptList) {
                                        event.getWaitList().addAccept(doc.getId(),event.getEventID());
                                    }

                                    List<DocumentSnapshot> declineList = decline.getDocuments();
                                    for (DocumentSnapshot doc: declineList) {
                                        event.getWaitList().addDecline(doc.getId(),event.getEventID());
                                    }

                                    List<DocumentSnapshot> pendingList = pending.getDocuments();
                                    for (DocumentSnapshot doc: pendingList) {
                                        event.getWaitList().addWaiting(doc.getId(),event.getEventID());
                                    }

                                    list.add(event);
                                    list.notifyDataSetChanged();
                                } else {
                                    Exception exception = task.getException();
                                }
                            }
                        });
                    }
                } else {
                    Exception exception = task.getException();
                }
            }
        });
    }

    // Updates an existing user's data
    public void resetImage(String deviceID) {
        Map<String, Object> data = new HashMap<>();
        data.put("imageURL", "NULL");
        usersCollection.document(deviceID).set(data, SetOptions.merge());
    }

    // Retrieves all users, which can be useful for admin functionality
    public Task<QuerySnapshot> getAllUsers() {
        return usersCollection.get();
    }






}

