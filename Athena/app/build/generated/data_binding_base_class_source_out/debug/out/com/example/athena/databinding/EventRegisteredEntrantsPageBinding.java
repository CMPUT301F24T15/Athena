// Generated by view binder compiler. Do not edit!
package com.example.athena.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.athena.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class EventRegisteredEntrantsPageBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ListView notifiedEntrantsListview;

  @NonNull
  public final TextView registeredEntrantsTitleNonEdit;

  @NonNull
  public final ConstraintLayout waitlistText;

  private EventRegisteredEntrantsPageBinding(@NonNull ConstraintLayout rootView,
      @NonNull ListView notifiedEntrantsListview, @NonNull TextView registeredEntrantsTitleNonEdit,
      @NonNull ConstraintLayout waitlistText) {
    this.rootView = rootView;
    this.notifiedEntrantsListview = notifiedEntrantsListview;
    this.registeredEntrantsTitleNonEdit = registeredEntrantsTitleNonEdit;
    this.waitlistText = waitlistText;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static EventRegisteredEntrantsPageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static EventRegisteredEntrantsPageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.event_registered_entrants_page, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static EventRegisteredEntrantsPageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.notified_entrants_listview;
      ListView notifiedEntrantsListview = ViewBindings.findChildViewById(rootView, id);
      if (notifiedEntrantsListview == null) {
        break missingId;
      }

      id = R.id.registered_entrants_title_non_edit;
      TextView registeredEntrantsTitleNonEdit = ViewBindings.findChildViewById(rootView, id);
      if (registeredEntrantsTitleNonEdit == null) {
        break missingId;
      }

      ConstraintLayout waitlistText = (ConstraintLayout) rootView;

      return new EventRegisteredEntrantsPageBinding((ConstraintLayout) rootView,
          notifiedEntrantsListview, registeredEntrantsTitleNonEdit, waitlistText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
