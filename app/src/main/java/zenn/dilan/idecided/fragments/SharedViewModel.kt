package zenn.dilan.idecided.fragments

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val dataListPlus = mutableListOf<Pair<String?, Float?>>()
    val dataListMinus = mutableListOf<Pair<String?, Float?>>()
    val dataQuadratPlus1 = mutableListOf<Pair<String?, Float?>>()
    val dataQuadratMinus1 = mutableListOf<Pair<String?, Float?>>()
    val dataQuadratMinus2 = mutableListOf<Pair<String?, Float?>>()
    val dataQuadratPlus2 = mutableListOf<Pair<String?, Float?>>()
}