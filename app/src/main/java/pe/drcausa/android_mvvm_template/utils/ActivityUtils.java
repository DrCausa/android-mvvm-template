package pe.drcausa.android_mvvm_template.utils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import pe.drcausa.android_mvvm_template.R;

public class ActivityUtils {
    public static void switchFragment(@NonNull AppCompatActivity parentActivity, Fragment fragment) {
        FragmentManager fm = parentActivity.getSupportFragmentManager();
        Fragment currentFragment = fm.findFragmentById(R.id.fragment_container);

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setReorderingAllowed(true);

        if (currentFragment != null && currentFragment.getClass().equals(fragment.getClass())) {
            transaction.detach(currentFragment)
                    .attach(currentFragment)
                    .commit();
            return;
        }

        transaction.replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
