// Generated by view binder compiler. Do not edit!
package com.example.athena.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.athena.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class AdminBrowseFacilitiesPageBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout adminSearchForOrganizersView;

  private AdminBrowseFacilitiesPageBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout adminSearchForOrganizersView) {
    this.rootView = rootView;
    this.adminSearchForOrganizersView = adminSearchForOrganizersView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static AdminBrowseFacilitiesPageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AdminBrowseFacilitiesPageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.admin_browse_facilities_page, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AdminBrowseFacilitiesPageBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    ConstraintLayout adminSearchForOrganizersView = (ConstraintLayout) rootView;

    return new AdminBrowseFacilitiesPageBinding((ConstraintLayout) rootView,
        adminSearchForOrganizersView);
  }
}
