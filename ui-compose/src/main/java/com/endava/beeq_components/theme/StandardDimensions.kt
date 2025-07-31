package endava.beeq.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import endava.beeq.compose.R

sealed interface StandardDimensions {

    @get:Composable
    val value: Dp

    object XS3 : StandardDimensions {
        override val value: Dp
            @Composable
            get() = dimensionResource(id = R.dimen.XS3)
    }

    object XS2 : StandardDimensions {
        override val value: Dp
            @Composable
            get() = dimensionResource(id = R.dimen.XS2)
    }

    object XS : StandardDimensions {
        override val value: Dp
            @Composable
            get() = dimensionResource(id = R.dimen.XS)
    }

    object S : StandardDimensions {
        override val value: Dp
            @Composable
            get() = dimensionResource(id = R.dimen.S)
    }

    object M : StandardDimensions {
        override val value: Dp
            @Composable
            get() = dimensionResource(id = R.dimen.M)
    }

    object L : StandardDimensions {
        override val value: Dp
            @Composable
            get() = dimensionResource(id = R.dimen.L)
    }

    object XL : StandardDimensions {
        override val value: Dp
            @Composable
            get() = dimensionResource(id = R.dimen.XL)
    }

    object XXL : StandardDimensions {
        override val value: Dp
            @Composable
            get() = dimensionResource(id = R.dimen.XXL)
    }

    object XXL2 : StandardDimensions {
        override val value: Dp
            @Composable
            get() = dimensionResource(id = R.dimen.XXL2)
    }

    object XXL3 : StandardDimensions {
        override val value: Dp
            @Composable
            get() = dimensionResource(id = R.dimen.XXL3)
    }

    object XXL4 : StandardDimensions {
        override val value: Dp
            @Composable
            get() = dimensionResource(id = R.dimen.XXL4)
    }

}