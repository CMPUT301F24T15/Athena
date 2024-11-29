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

public final class WaitlistEntrantsToNotifyPageBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ListView entrantsToNotifyListview;

  @NonNull
  public final TextView entrantsToNotifyNonEdit;

  private WaitlistEntrantsToNotifyPageBinding(@NonNull ConstraintLayout rootView,
      @NonNull ListView entrantsToNotifyListview, @NonNull TextView entrantsToNotifyNonEdit) {
    this.rootView = rootView;
    this.entrantsToNotifyListview = entrantsToNotifyListview;
    this.entrantsToNotifyNonEdit = entrantsToNotifyNonEdit;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static WaitlistEntrantsToNotifyPageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static WaitlistEntrantsToNotifyPageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.waitlist_entrants_to_notify_page, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static WaitlistEntrantsToNotifyPageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.entrants_to_notify_listview;
      ListView entrantsToNotifyListview = ViewBindings.findChildViewById(rootView, id);
      if (entrantsToNotifyListview == null) {
        break missingId;
      }

      id = R.id.entrants_to_notify_non_edit;
      TextView entrantsToNotifyNonEdit = ViewBindings.findChildViewById(rootView, id);
      if (entrantsToNotifyNonEdit == null) {
        break missingId;
      }

      return new WaitlistEntrantsToNotifyPageBinding((ConstraintLayout) rootView,
          entrantsToNotifyListview, entrantsToNotifyNonEdit);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
