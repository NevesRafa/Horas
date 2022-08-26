package com.nevesrafael.horas.screens.homeScreen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.nevesrafael.horas.HomeScreenAdapter
import com.nevesrafael.horas.R
import com.nevesrafael.horas.databinding.ActivityHomeScreenBinding
import com.nevesrafael.horas.model.Workday
import com.nevesrafael.horas.screens.createWorkday.CreateWorkdayFragment
import com.nevesrafael.horas.screens.infoScreen.InfoWorkdayFragment
import com.nevesrafael.horas.screens.searchPeriod.SearchPeriodFragment
import com.nevesrafael.horas.screens.sumHoursDays.SumHoursDaysFragment

lateinit var binding: ActivityHomeScreenBinding
lateinit var homeScreenAdapter: HomeScreenAdapter
lateinit var presenter: HomeScreenPresenter

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = HomeScreenPresenter(this)

        configureFabHomeScreen()
        configureRecyclerViewWorkday()
        appBarButton()
    }

    override fun onResume() {
        super.onResume()
        presenter.updateListOnTheScreen()
    }

    private fun appBarButton() {
        binding.bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    val searchPeriodFragment = SearchPeriodFragment()
                    searchPeriodFragment.show(supportFragmentManager, null)
                    true
                }
                R.id.sum -> {
                    val sumHoursDaysFragment = SumHoursDaysFragment()
                    sumHoursDaysFragment.show(supportFragmentManager, null)
                    true
                }
                else -> false
            }
        }
    }

    private fun configureFabHomeScreen() {
        binding.fabAddEvento.setOnClickListener {

            val createWorkdayFragment = CreateWorkdayFragment(clickOnSave = { workday ->
                presenter.saveWorkday(workday)
            })
            createWorkdayFragment.show(supportFragmentManager, null)
        }
    }

    fun showListWorkdays(listWorkdays: List<Workday>) {

        homeScreenAdapter.update(listWorkdays)
    }

    private fun configureRecyclerViewWorkday() {

        binding.recyclerWorkedDays.layoutManager = LinearLayoutManager(this)

        homeScreenAdapter = HomeScreenAdapter(shortClick = { workday ->
            sendFomInfoFragment(workday)
        },
            longClick = { workday, itemClicked ->
                showPopupMenu(itemClicked, workday)
            })

        binding.recyclerWorkedDays.adapter = homeScreenAdapter
    }

    private fun sendFomInfoFragment(workday: Workday) {
        val intentData = Bundle()
        intentData.putParcelable(InfoWorkdayFragment.EXTRA_WORKDAY_RECEIVED_ID, workday)

        val fragment = InfoWorkdayFragment()

        fragment.arguments = intentData
        fragment.show(supportFragmentManager, null)
    }

    private fun showPopupMenu(itemClicked: View, workday: Workday) {

        val popup = PopupMenu(this, itemClicked)
        popup.menuInflater.inflate(R.menu.menu_edit_remove, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            presenter.clickMenu(menuItem, workday)
            return@setOnMenuItemClickListener true
        }
        popup.show()
    }

    fun showModalToEdit(workdayForEdit: Workday) {

        val intentData = Bundle()
        intentData.putParcelable(CreateWorkdayFragment.EXTRA_WORKDAY_EDIT, workdayForEdit)

        val fragment = CreateWorkdayFragment(clickOnSave = { workdayEdited ->
            presenter.clickOnEdit(workdayEdited)
        })
        fragment.arguments = intentData
        fragment.show(supportFragmentManager, null)
    }
}