/**
 * This Fragment is responsible for browsing the list of users in the admin profile screen.
 * It fetches a list of users from the Firebase Firestore database and displays them in a ListView.
 * When a user is clicked, it navigates to a detailed user profile page.
 */

 package com.example.athena.AdminFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.athena.ArrayAdapters.UserArrayAdapter;
import com.example.athena.Firebase.UserDB;
import com.example.athena.Models.User;
import com.example.athena.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class AdminProfileBrowse extends Fragment {
    private String deviceID;
    public UserDB userDB;
    public ArrayList<User> users;
    private ListView listView;
    public ArrayList<String> usersID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_view, container, false);
        super.onCreate(savedInstanceState);
        return view;
    }

    public void onViewCreated (@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        assert bundle != null;
        this.deviceID = bundle.getString("deviceID");
        userDB = new UserDB();
        users = new ArrayList<>();
        usersID = new ArrayList<>();

        listView = view.findViewById(R.id.myEventList);

        Task getUserList = userDB.getUserList();
        getUserList.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    UserArrayAdapter userAdapter = new UserArrayAdapter(getContext(), users);
                    listView.setAdapter(userAdapter);
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        String name = documentSnapshot.getString("name");
                        String email = documentSnapshot.getString("email");
                        String phone = documentSnapshot.getString("phone");
                        String imageURL = documentSnapshot.getString("imageURL");


                        User user = new User(name, email, phone, imageURL);
                        users.add(user);
                        usersID.add(documentSnapshot.getId());
                    }
                    userAdapter.notifyDataSetChanged();
                }
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TO - DO
                bundle.putString("userID",usersID.get(position));
                displayChildFragment(new AdminProfileDetail(),bundle);
            }
        });

    }
    public void displayChildFragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

}

