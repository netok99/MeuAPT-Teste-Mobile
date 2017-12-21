package com.meuapttestemobile.presentation.Shot

import com.meuapttestemobile.data.model.Shot
import com.meuapttestemobile.presentation.BaseView

interface ShotContract {
    interface View : BaseView {
        fun setupShotList(gists: List<Shot>)

        fun showError()
    }

    interface Presenter {
        fun getShots(accessToken: String, page: Int)
    }
}
