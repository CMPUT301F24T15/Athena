package com.example.athena.AdminFragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.athena.Firebase.FacilitiesDB;
import com.example.athena.Firebase.EventsDB;
import com.example.athena.Firebase.UserDB;
import com.example.athena.GeneralActivities.MainActivity;
import com.example.athena.Models.Event;
import com.example.athena.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

/**
 *
 */
public class FacilityDetailsAdmin extends Fragment {
    private EventsDB eventsDB;
    private FacilitiesDB facilitiesDB;
    private UserDB usersDB;
    private String deviceID;
    private String facilityID;
    private Bundle bundle;
    private boolean isAdmin;
    private String organizer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.facility_details_admin, container, false);
        super.onCreate(savedInstanceState);
        return view;
    }

    public void onViewCreated (@NonNull View view, Bundle savedInstanceState){
        TextView facilityName = view.findViewById(R.id.facility_name_textview_admin);
        Button back = view.findViewById(R.id.back_from_facility_details);
        Button delete = view.findViewById(R.id.delete_facility_button);
        TextView facilityLocation = view.findViewById(R.id.facility_location_textView_admin);

        super.onViewCreated(view, savedInstanceState);
        bundle = getArguments();
        organizer = bundle.getString("facilityOrganizer");
        assert bundle!= null;
        deviceID = bundle.getString("deviceID");
        facilityID = bundle.getString("facilityID");
        isAdmin = bundle.getBoolean("isAdmin");
        facilitiesDB = new FacilitiesDB();
        usersDB = new UserDB();
        eventsDB = new EventsDB();

        Task facilityDetails = facilitiesDB.getFacility(facilityID);
        facilityDetails.addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = (DocumentSnapshot) task.getResult();
                    facilityName.setText(document.getString("facilityName"));
                    facilityLocation.setText(document.getString("facilityLocation"));
                } else {
                    Exception e = task.getException();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayChildFragment(new AdminBrowseFacilities(), bundle);

            }
        });

    }


    /**
     * This method handles the deletion of all facilities
     */
    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("DELETE FACILITY?");

        final TextView text = new TextView(requireContext());
        text.setText("\nAre you sure you want to delete this facility?");
        builder.setView(text);

        builder.setPositiveButton("YES", (dialog, which) -> {

            facilitiesDB.deleteFacility(facilityID);
            usersDB.deleteOrgFacility(organizer);

            ///deletes all of the events at a given facility
            Task getEvents = eventsDB.getEventsList();
            getEvents.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task){
                    if (task.isSuccessful()) {
                        for(DocumentSnapshot event: task.getResult().getDocuments()) {
                            String eventID = event.getId();
                            if((event.contains("facility"))){
                                String eventFacility = event.getString("facility");
                                if (eventFacility.equals(facilityID)) {
                                    eventsDB.deleteSingularEvent(eventID);
                                }

                            }
                        }

                    }else{
                        Exception e = task.getException();
                    }
                }
            });

            displayChildFragment(new AdminBrowseFacilities(), bundle);

        });
        builder.setNeutralButton("CANCEL", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    public void displayChildFragment(Fragment fragment, Bundle bundle){
        fragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction() .replace(R.id.content_frame, fragment).commit();
    }
}