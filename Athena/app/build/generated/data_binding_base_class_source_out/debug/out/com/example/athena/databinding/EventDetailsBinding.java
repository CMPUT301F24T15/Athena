// Generated by view binder compiler. Do not edit!
package com.example.athena.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public final class EventDetailsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView OrgViewEventDescription;

  @NonNull
  public final TextView OrgViewEventDescriptionNonEdit;

  @NonNull
  public final TextView OrgViewEventName;

  @NonNull
  public final LinearLayout eventCardLayoutOrgView;

  @NonNull
  public final TextView eventName;

  @NonNull
  public final ImageView eventPoster;

  @NonNull
  public final ConstraintLayout linearLayout;

  @NonNull
  public final ImageView qrCodeView;

  @NonNull
  public final ConstraintLayout registrationStartDateTextview;

  private EventDetailsBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView OrgViewEventDescription, @NonNull TextView OrgViewEventDescriptionNonEdit,
      @NonNull TextView OrgViewEventName, @NonNull LinearLayout eventCardLayoutOrgView,
      @NonNull TextView eventName, @NonNull ImageView eventPoster,
      @NonNull ConstraintLayout linearLayout, @NonNull ImageView qrCodeView,
      @NonNull ConstraintLayout registrationStartDateTextview) {
    this.rootView = rootView;
    this.OrgViewEventDescription = OrgViewEventDescription;
    this.OrgViewEventDescriptionNonEdit = OrgViewEventDescriptionNonEdit;
    this.OrgViewEventName = OrgViewEventName;
    this.eventCardLayoutOrgView = eventCardLayoutOrgView;
    this.eventName = eventName;
    this.eventPoster = eventPoster;
    this.linearLayout = linearLayout;
    this.qrCodeView = qrCodeView;
    this.registrationStartDateTextview = registrationStartDateTextview;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static EventDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static EventDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.event_details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static EventDetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.OrgViewEventDescription;
      TextView OrgViewEventDescription = ViewBindings.findChildViewById(rootView, id);
      if (OrgViewEventDescription == null) {
        break missingId;
      }

      id = R.id.OrgViewEventDescriptionNonEdit;
      TextView OrgViewEventDescriptionNonEdit = ViewBindings.findChildViewById(rootView, id);
      if (OrgViewEventDescriptionNonEdit == null) {
        break missingId;
      }

      id = R.id.OrgViewEventName;
      TextView OrgViewEventName = ViewBindings.findChildViewById(rootView, id);
      if (OrgViewEventName == null) {
        break missingId;
      }

      id = R.id.event_card_layout_org_view;
      LinearLayout eventCardLayoutOrgView = ViewBindings.findChildViewById(rootView, id);
      if (eventCardLayoutOrgView == null) {
        break missingId;
      }

      id = R.id.eventName;
      TextView eventName = ViewBindings.findChildViewById(rootView, id);
      if (eventName == null) {
        break missingId;
      }

      id = R.id.event_poster;
      ImageView eventPoster = ViewBindings.findChildViewById(rootView, id);
      if (eventPoster == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      ConstraintLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.qrCodeView;
      ImageView qrCodeView = ViewBindings.findChildViewById(rootView, id);
      if (qrCodeView == null) {
        break missingId;
      }

      ConstraintLayout registrationStartDateTextview = (ConstraintLayout) rootView;

      return new EventDetailsBinding((ConstraintLayout) rootView, OrgViewEventDescription,
          OrgViewEventDescriptionNonEdit, OrgViewEventName, eventCardLayoutOrgView, eventName,
          eventPoster, linearLayout, qrCodeView, registrationStartDateTextview);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
