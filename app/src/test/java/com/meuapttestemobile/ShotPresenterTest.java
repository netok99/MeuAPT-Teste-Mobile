package com.meuapttestemobile;

import com.meuapttestemobile.data.dataSource.ShotDataSource;
import com.meuapttestemobile.data.model.Image;
import com.meuapttestemobile.data.model.Shot;
import com.meuapttestemobile.presentation.Shot.ShotPresenter;
import com.meuapttestemobile.presentation.Shot.ShotPresenterContract;
import com.meuapttestemobile.presentation.Shot.ShotViewContract;

import org.junit.Before;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;

import rx.Observable;

public class ShotPresenterTest {

    private ShotViewContract view;
    private ShotPresenterContract presenter;
    private ShotDataSource dataSource;

    @Before
    public void setup() {
        view = mock(ShotViewContract.class);
        dataSource = mock(ShotDataSource.class);
        presenter = new ShotPresenter(view, dataSource);
    }

    private final ArrayList<Shot> shots = new ArrayList<Shot>() {{
        add(new Shot("teste1", new Image("imageHidpi1", "imageNormal1"),
                10, new Date(), "description1", 15));
        add(new Shot("teste2", new Image("imageHidpi2", "imageNormal2"),
                15, new Date(), "description2", 20));
        add(new Shot("teste3", new Image("imageHidpi3", "imageNormal3"),
                16, new Date(), "description3", 23));
    }};

    @Test
    public void shouldGetGistList() {
        int page = 1;
        final String accessTokenFake = "djsabdjdfkdsfjd";
        Mockito.when(dataSource.getShots(accessTokenFake, page))
                .thenReturn(Observable.just(shots));

        presenter.getShots(accessTokenFake, page);

        Mockito.verify(view).showLoadingIndicator(true);
        Mockito.verify(dataSource).getShots(accessTokenFake, page);
        Mockito.verify(view).setupShotList(shots);
        Mockito.verify(view).showLoadingIndicator(false);
        Mockito.verifyNoMoreInteractions(view, dataSource);
    }
}
