package elfak.mosis.myplaces

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ListView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import elfak.mosis.myplaces.databinding.FragmentListBinding
import elfak.mosis.myplaces.elfak.mosis.myplaces.data.MyPlace
import elfak.mosis.myplaces.elfak.mosis.myplaces.model.MyPlacesViewModel
import android.widget.ArrayAdapter as ArrayAdapter


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    //private lateinit var places: ArrayList<String>
    private val myPlacesViewModel: MyPlacesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_new_place -> {
                this.findNavController().navigate(R.id.action_ListFragment_to_EditFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.action_my_places_list)
        item.isVisible = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView")
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated")

        val myPlacesList: ListView = requireView().findViewById<ListView>(R.id.my_places_list)
        myPlacesList.adapter= ArrayAdapter<MyPlace>(view.context, android.R.layout.simple_list_item_1, myPlacesViewModel.myPlacesList)
    }

    override fun onDestroyView() {
        Log.i(TAG, "onDestroyView")
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy")
        super.onDestroy()
    }

    companion object {
        val TAG = "ListFragment"
    }
}