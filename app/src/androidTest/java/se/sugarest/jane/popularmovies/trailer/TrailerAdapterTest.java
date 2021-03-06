package se.sugarest.jane.popularmovies.trailer;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import se.sugarest.jane.popularmovies.R;

/**
 * Created by jane on 17-8-15.
 */

@RunWith(AndroidJUnit4.class)
public class TrailerAdapterTest {

    private Context instrumentationCtx;
    private TrailerAdapter trailerAdapter;
    private List<Trailer> trailerData = new ArrayList<>();

    @Before
    public void setUp() {
        //getContext() only gets unit test's context.
        instrumentationCtx = InstrumentationRegistry.getTargetContext();
        // have to write this line of code inside setUp(),
        // otherwise, getTrailerFabBackgroundColorText() cannot find the context and resources
        trailerAdapter = new TrailerAdapter(null, instrumentationCtx);
    }

    /***********************************************************************************************
     *                                                                                             *
     * trailerData == null happens when trailerData hasn't been loaded yet, it won't               *
     * trigger trailerAdapter.setTrailerData(trailerData) method.                                  *
     * The getItemCountTest_noTrailerData() method is testing the situation that                   *
     * trailerData != null but trailerData.size == 0,                                              *
     * which means the trailerData has already been loaded, but the movie doesn't have trailers.   *
     * So we don't initialize trailerData, just pass it to setTrailerData(trailerData),            *
     * meaning no trailers available.                                                              *
     *                                                                                             *
     ***********************************************************************************************/

    @Test
    public void getItemCountTest_nullTrailerData() throws Exception {
        // trailerData == null;
        Assert.assertEquals(0, trailerAdapter.getItemCount());
    }

    @Test
    public void getItemCountTest_noTrailerData() throws Exception {
        trailerAdapter.setTrailerData(trailerData);
        Assert.assertEquals(0, trailerAdapter.getItemCount());
    }

    @Test
    public void getItemCountTest_oneTrailer() throws Exception {
        String oneTrailerKey = "c38r-SAnTWM";
        Trailer trailer = new Trailer(oneTrailerKey);
        trailerData.add(trailer);
        trailerAdapter.setTrailerData(trailerData);
        Assert.assertEquals(1, trailerAdapter.getItemCount());
    }

    @Test
    public void getItemCountTest_twoTrailer() throws Exception {
        String oneTrailerKey = "c38r-SAnTWM";
        String secondTrailerKey = "bgeSXHvPoBI";
        Trailer trailerOne = new Trailer(oneTrailerKey);
        Trailer trailerTwo = new Trailer(secondTrailerKey);
        trailerData.add(trailerOne);
        trailerData.add(trailerTwo);
        trailerAdapter.setTrailerData(trailerData);
        Assert.assertEquals(2, trailerAdapter.getItemCount());
    }

    @Test
    public void getTrailerFabBackgroundColorText() throws Exception {
        int position = 5;
        int expectedBgColor = ContextCompat.getColor(instrumentationCtx, R.color.trailer5);
        int realBgColor = trailerAdapter.getTrailerFabBackgroundColor(position);
        Assert.assertEquals(expectedBgColor, realBgColor);
    }
}