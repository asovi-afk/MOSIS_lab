package elfak.mosis.myplaces.elfak.mosis.myplaces.model

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.ViewModel
import elfak.mosis.myplaces.ListFragment
import elfak.mosis.myplaces.R
import elfak.mosis.myplaces.elfak.mosis.myplaces.data.MyPlace

class MyPlacesViewModel: ViewModel() {
    var myPlacesList: ArrayList<MyPlace> = ArrayList<MyPlace>()

    fun addPlace(place:MyPlace){
        myPlacesList.add(place)
    }
}