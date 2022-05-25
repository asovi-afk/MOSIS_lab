package elfak.mosis.myplaces

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import elfak.mosis.myplaces.databinding.ActivityMainBinding
import elfak.mosis.myplaces.elfak.mosis.myplaces.model.MyPlacesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val myPlacesViewModel: MyPlacesViewModel by viewModels() // Korak 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)


        navController.addOnDestinationChangedListener{ controller, destination, arguments ->
            if(destination.id == R.id.EditFragment || destination.id == R.id.viewFragment)
                binding.fab.hide()
            else
                binding.fab.show()

            // Korak 8
            if(destination.id == R.id.EditFragment)
                if(myPlacesViewModel.selected != null){
                    controller.graph.findNode(R.id.EditFragment)?.label = getString(R.string.eddit_my_place_label)
                    Toast.makeText(this, "Za edit", Toast.LENGTH_SHORT).show()
                }
                else {
                    controller.graph.findNode(R.id.EditFragment)?.label = getString(R.string.add_my_place_label)
                    Toast.makeText(this, "Za add", Toast.LENGTH_SHORT).show()
                }
        }
        setupActionBarWithNavController(navController, appBarConfiguration) // Mora posle koraka 8

        binding.fab.setOnClickListener { view ->
            if(navController.currentDestination?.id == R.id.HomeFragment)
                navController.navigate(R.id.action_HomeFragment_to_EditFragment)
            else if(navController.currentDestination?.id == R.id.ListFragment)
                navController.navigate(R.id.action_ListFragment_to_EditFragment)
        }
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //menuInflater.inflate(R.menu.menu_main, menu)
        menuInflater.inflate(R.menu.menu_my_places_list, menu)
        return true
    } */

      override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       when (item.itemId) {
            R.id.action_show_map -> Toast.makeText(this, "Show Map!", Toast.LENGTH_SHORT).show()
           /* R.id.action_new_place -> Toast.makeText(this, "New Place!", Toast.LENGTH_SHORT).show()

            R.id.action_my_places_list -> {
                this.findNavController(R.id.nav_host_fragment_content_main, ).navigate(R.id.action_HomeFragment_to_ListFragment)
            } */
            R.id.action_about -> {
                val i: Intent = Intent(this, About::class.java)
                startActivity(i)
            }
       }
          return super.onOptionsItemSelected(item)
      }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}