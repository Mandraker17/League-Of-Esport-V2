package fr.cdsm.leagueofesportv2.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import fr.cdsm.leagueofesportv2.R;
import fr.cdsm.leagueofesportv2.fragment.FragmentAccueil;
import fr.cdsm.leagueofesportv2.fragment.FragmentListe;

public class MainActivity extends AppCompatActivity {

    String[] drawerItemsList;
    ListView myDrawer;
    DrawerLayout drawerLayout;
    Fragment fr;
    FragmentManager fm = getFragmentManager();
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerItemsList = getResources().getStringArray(R.array.items);
        myDrawer = (ListView) findViewById(R.id.my_drawer);
        myDrawer.setAdapter(new ArrayAdapter<String>(this, R.layout.item_drawer, drawerItemsList));
        myDrawer.setOnItemClickListener(new MyDrawerItemClickListener());
    }

    private class MyDrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    fr = new FragmentAccueil();
                    break;

                case 1:
                    fr = new FragmentListe();
                    break;

            }
            fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_main, fr);
            fragmentTransaction.commit();
            drawerLayout.closeDrawer(myDrawer);
        }
    }

}
