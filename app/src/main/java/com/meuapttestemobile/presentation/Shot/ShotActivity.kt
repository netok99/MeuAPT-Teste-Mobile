package com.meuapttestemobile.presentation.Shot

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.meuapttestemobile.R
import com.meuapttestemobile.data.model.Shot
import com.meuapttestemobile.presentation.BaseActivity
import com.meuapttestemobile.presentation.PresenterModule
import com.meuapttestemobile.presentation.UiComponent
import kotlinx.android.synthetic.main.activity_shots.*
import javax.inject.Inject

class ShotActivity : BaseActivity(), ShotContract.View {

    private lateinit var uiComponent: UiComponent
    @Inject
    lateinit var presenter: ShotContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shots)
        uiComponent = getAppComponent() + PresenterModule(this)
        uiComponent.inject(this)

        setupUI()
        getShots()
    }

    private fun setupUI() {
        setupToolbar()
        setupSwipeRefresh()
    }

    private fun setupToolbar() {
        toolbar.title = getString(R.string.title_shot_activity)
        setSupportActionBar(toolbar)
    }

    private fun setupSwipeRefresh() = swipeRefresh.setOnRefreshListener({ getShots() })

    private fun getShots() = presenter.getShots(
            "84ec8334d80acb8d849f9d2f24038d25df170bcce1b3d0d998543cfb81bb7e8e", 1)

    override fun setupShotList(gists: List<Shot>) {
        val layoutManager = LinearLayoutManager(this)
        shotsList.layoutManager = layoutManager
        shotsList.addItemDecoration(DividerItemDecoration(shotsList.context, layoutManager.orientation))
        shotsList.adapter = ShotAdapter(this, gists)
        shotsList.setHasFixedSize(true)
    }

    override fun showError() = Toast.makeText(this, getString(R.string.error_message_shot),
            Toast.LENGTH_LONG).show()

    override fun showLoadingIndicator(isLoading: Boolean) {
        swipeRefresh.isRefreshing = isLoading
    }
}
