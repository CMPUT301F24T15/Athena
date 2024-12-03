package com.example.athena.EntrantAndOrganizerFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.athena.Firebase.EventsDB;
import com.example.athena.Firebase.UserDB;
import com.example.athena.Models.Event;
import com.example.athena.R;
import com.example.athena.WaitList.UserInviteArrayAdapter;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

/**
*This fragment represents the user's view of the events they are invited to attend.
*It handles displaying a list of events where the user has been invited,
*allowing them to either accept or decline the invitation.
 */
public class UserViewAttendingEventsFragment extends Fragment{

    public ListView invites;
    private UserInviteArrayAdapter adapter;
    public ArrayList<Event> events;
    public String deviceID;
    public UserDB userDB;
    public EventsDB eventsDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.user_my_notis_fragment, container, false);
        invites = view.findViewById(R.id.user_notifications_listview);
        Bundle bundle = getArguments();
        assert bundle != null;
        this.deviceID = bundle.getString("deviceID");
        super.onCreate(savedInstanceState);
        ///Inflates the layout for the fragment
        return view;
    }
    public void onViewCreated (@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        ///These are representative of database connections used for the respective entities (Users, events, and events)
        userDB = new UserDB();
        eventsDB = new EventsDB();
        events = new ArrayList<>();

        Task getUserEvents = userDB.getUserEvents(deviceID);
        Task getEventList = eventsDB.getEventsList();

        ///Get event list
        adapter = new UserInviteArrayAdapter(getContext(), events);
        invites.setAdapter(adapter);

        //load invited events
        userDB.getUserInvitedEvents(deviceID,adapter);


        invites.setClickable(Boolean.TRUE);
        invites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /* we create a new task here to grab the info for the waitlist then attach it to the event
                * we are doing this here instead of where we are doing all the other db call as to do this call we need to know what event to search through
                * so instead we will do when the user clicks on the event.
                 */
                showAcceptDialog(events.get(position));

            }
        });


    }


    /**
     * show a popup dialog allowing the user to accept or decline invites
     * @param event the event the user is accepting or declining
     */
    public void showAcceptDialog(Event event){
        // display popup
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(String.format("You Are Invited to %s!", event.getEventName()));
        final TextView desc = new TextView(getActivity());
        desc.setText(String.format("you have been invited to %s. Do you wish to accept or decline the invite?", event.getEventDescription()));
        builder.setView(desc);

        // Set up buttons
        builder.setPositiveButton("Accept", (dialog, which) -> {
            eventsDB.moveUserID("invited","accepted",deviceID, event.getEventID());
            event.getWaitList().moveUserFromInvited(deviceID,"accepted");
            userDB.changeEventStatusAccepted(event.getEventID(), deviceID);
            adapter.remove(event);
            adapter.notifyDataSetChanged();

        });
        builder.setNegativeButton("Decline",(dialog, which) ->{
            eventsDB.moveUserID("invited","declined",deviceID, event.getEventID());
            event.getWaitList().moveUserFromInvited(deviceID,"declined");
            userDB.changeEventStatusDeclined(event.getEventID(), deviceID);
            adapter.remove(event);
            adapter.notifyDataSetChanged();

        });
        builder.setNeutralButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}

