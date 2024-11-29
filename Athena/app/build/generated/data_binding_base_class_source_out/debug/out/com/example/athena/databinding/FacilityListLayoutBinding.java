// Generated by view binder compiler. Do not edit!
package com.example.athena.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public final class FacilityListLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout facilityLayoutConstraint;

  @NonNull
  public final TextView facilityLocationInList;

  @NonNull
  public final TextView facilityNameInList;

  @NonNull
  public final LinearLayout linearLayout2;

  @NonNull
  public final TextView organizerIdInList;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView20;

  @NonNull
  public final TextView textView22;

  private FacilityListLayoutBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout facilityLayoutConstraint, @NonNull TextView facilityLocationInList,
      @NonNull TextView facilityNameInList, @NonNull LinearLayout linearLayout2,
      @NonNull TextView organizerIdInList, @NonNull TextView textView2,
      @NonNull TextView textView20, @NonNull TextView textView22) {
    this.rootView = rootView;
    this.facilityLayoutConstraint = facilityLayoutConstraint;
    this.facilityLocationInList = facilityLocationInList;
    this.facilityNameInList = facilityNameInList;
    this.linearLayout2 = linearLayout2;
    this.organizerIdInList = organizerIdInList;
    this.textView2 = textView2;
    this.textView20 = textView20;
    this.textView22 = textView22;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FacilityListLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FacilityListLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.facility_list_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FacilityListLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout facilityLayoutConstraint = (ConstraintLayout) rootView;

      id = R.id.facility_location_in_list;
      TextView facilityLocationInList = ViewBindings.findChildViewById(rootView, id);
      if (facilityLocationInList == null) {
        break missingId;
      }

      id = R.id.facility_name_in_list;
      TextView facilityNameInList = ViewBindings.findChildViewById(rootView, id);
      if (facilityNameInList == null) {
        break missingId;
      }

      id = R.id.linearLayout2;
      LinearLayout linearLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout2 == null) {
        break missingId;
      }

      id = R.id.organizer_id_in_list;
      TextView organizerIdInList = ViewBindings.findChildViewById(rootView, id);
      if (organizerIdInList == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView20;
      TextView textView20 = ViewBindings.findChildViewById(rootView, id);
      if (textView20 == null) {
        break missingId;
      }

      id = R.id.textView22;
      TextView textView22 = ViewBindings.findChildViewById(rootView, id);
      if (textView22 == null) {
        break missingId;
      }

      return new FacilityListLayoutBinding((ConstraintLayout) rootView, facilityLayoutConstraint,
          facilityLocationInList, facilityNameInList, linearLayout2, organizerIdInList, textView2,
          textView20, textView22);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
