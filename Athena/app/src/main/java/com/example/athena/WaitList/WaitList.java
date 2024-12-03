package com.example.athena.WaitList;

import com.example.athena.Models.Event;


import java.util.ArrayList;

/**
 * This class is responsible for storing information about waitlists for corresponding events
 * It has methods which are used to retrieve lists, as well as handle changes made to the waitlists
 */
public class WaitList{
    private ArrayList<String> waiting;
    private ArrayList<String> invited;
    private ArrayList<String> declined;
    private ArrayList<String> accepted;
    private Event attachedEvent;

    ///This is a constructor for the waitlist class, it initializes it waitlist with 3 categories:
    ///entrants who have been sent notifications, entrants who are waiting to be notified,
    ///and entrants who have denied invitations
    public WaitList(Event event){
        waiting = new ArrayList<String>();
        invited = new ArrayList<String>();
        declined = new ArrayList<String>();
        accepted = new ArrayList<String>();

        attachedEvent = event;
    }

    /**
     * set the declined arraylist to the the param
     * @param declined arraylist of deviceIDs whos status is declined
     */
    public void setDeclined(ArrayList<String> declined) {
        this.declined = declined;
    }

    /**
     * set the accepted arraylist to the the param
     * @param accepted arraylist of deviceIDs whos status is accepted
     */
    public void setAccepted(ArrayList<String> accepted) {
        this.accepted = accepted;
    }

    /**
     * set the invited arraylist to the the param
     * @param invited arraylist of deviceIDs whos status is invited
     */
    public void setInvited(ArrayList<String> invited) {
        this.invited = invited;
    }

    /**
     * set the waiting arraylist to the the param
     * @param waiting arraylist of deviceIDs whos status is pending
     */
    public void setWaiting(ArrayList<String> waiting) {
        this.waiting = waiting;
    }

    /**
     *  get ArrayList of DeviceID that status is declined
     * @return returns an arraylist of deviceIDs who's status is declined
     */
    public ArrayList<String> getDeclined() {
        return declined;
    }

    /**
     *  get ArrayList of DeviceID that status is declined
     * @return returns an arraylist of deviceIDs who's status is declined
     */
    public ArrayList<String> getWaiting() {
        return waiting;
    }
    /**
     *  get ArrayList of DeviceID that status is accepted
     * @return returns an arraylist of deviceIDs who's status is accepted
     */
    public ArrayList<String> getAccepted() {
        return accepted;
    }
    /**
     *  get ArrayList of DeviceID that status is invited
     * @return returns an arraylist of deviceIDs who's status is invited
     */
    public ArrayList<String> getInvited() {
        return invited;
    }

    /**
     * add a deviceID to the waiting list
     * @param userID the deviceID/userID of the user to be added
     * @param eventID the eventID for which event
     */
    public void addWaiting(String userID, String eventID) {

        this.waiting.add(userID);


    }

    /**
     * add a deviceID to the invited list
     * @param userID the deviceID/userID of the user to be added
     * @param eventID the eventID for which event
     */
    public void addInvited(String userID, String eventID) {

        this.invited.add(userID);


    }

    /**
     * add a deviceID to the accepted list
     * @param userID the deviceID/userID of the user to be added
     * @param eventID the eventID for which event
     */
    public void addAccept(String userID, String eventID) {

        this.accepted.add(userID);


    }

    /**
     * add a deviceID to the declined list
     * @param userID the deviceID/userID of the user to be added
     * @param eventID the eventID for which event
     */
    public void addDecline(String userID, String eventID) {

        this.declined.add(userID);


    }



    /**
     * remove a user from the waiting list
     * @param userID the deviceID/userID of the user to be added
     * @param eventID the eventID for which event
     */
    public void removeUser(String userID, String eventID){

        this.waiting.remove(userID);

    }


    /**
     * Randomly selects x amount of users to be sent a invitation to the event moving them from pending to chosen
     * @param numSelect the number of users to be selected
     */
    public void selectUsersToInvite(int numSelect, String eventID){

        if (numSelect > waiting.size()){

            //if number to select is greater than amount signed up send all to invited
            while (!waiting.isEmpty()){
                moveUsers(waiting.get(0),invited,waiting);
            }
        }else {
            //get random from list and move it
            for (int i = 0; i < numSelect; i++) {
                int indexNum = (int) (Math.random() * waiting.size());
                moveUsers(waiting.get(indexNum), invited, waiting);

            }
        }

    }



    /**
     * Moves user object from one array list to another
     * @param userId userId string to be moved
     * @param receiver array list that adds the user
     * @param donor array list that removes the user
     */
    private void moveUsers(String userId, ArrayList<String> receiver, ArrayList<String> donor){
        receiver.add(userId);
        donor.remove(userId);
    }
    /**
     * moves the user from chosen to either declined or accepted
     * @param userID the users unique ID
     * @param newStatus whether the user accepted or declined the event
     */
    public void moveUserFromInvited(String userID, String newStatus){
        if(newStatus == "accepted"){
            moveUsers(userID,accepted,invited);
        } else {
            moveUsers(userID,declined,invited);
        }

    }
}
