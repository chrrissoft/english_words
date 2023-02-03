package com.chrrissoft.englishwords.gameplay.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chrrissoft.englishwords.R.string.levels_step__gameplay
import com.chrrissoft.englishwords.gameplay.ui.keyboard.Keyboard
import com.chrrissoft.englishwords.report.ui.ReportScreen
import com.chrrissoft.englishwords.util.middle
import com.chrrissoft.englishwords.util.px
import com.chrrissoft.inglishwords.data.session.NATIVE_REPORTS
import com.chrrissoft.inglishwords.data.session.TARGET_REPORTS
import com.chrrissoft.inglishwords.data.session.TOTAL_REPORTS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamePlayScreen(
    viewModel: GamePlayViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    val target by TARGET_REPORTS.collectAsState()
    val native by NATIVE_REPORTS.collectAsState()
    val total by TOTAL_REPORTS.collectAsState()

    if (target != null && native != null && total != null) {
        ReportScreen(target!!, native!!, total!!)
    } else {
        Scaffold(topBar = {
            CenterAlignedTopAppBar(
                title = {
                    val totalLevels = "${state.level.total}"
                    val current = "${state.level.current}"
                    Text(
                        fontWeight = Bold,
                        color = colorScheme.onSecondaryContainer,
                        text = stringResource(levels_step__gameplay).format(current, totalLevels),
                    )
                },
                colors = centerAlignedTopAppBarColors(colorScheme.secondaryContainer),
            )
        }) {
            Box(
                Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                GameState(state)

                val editorWidth = LocalConfiguration.current.screenWidthDp.div(2).dp
                val editorHeight = LocalConfiguration.current.screenHeightDp.div(5).dp
                val editorWidthMiddle = editorWidth.middle.px
                val editorHeightMiddle = editorHeight.middle.px
                Editor(
                    state = state.editor,
                    translation = state.translated,
                    modifier = Modifier
                        .width(editorWidth)
                        .height(editorHeight)
                        .align { _, size, _ ->
                            IntOffset(
                                size.width
                                    .div(2)
                                    .minus(editorWidthMiddle),
                                size.height
                                    .div(3)
                                    .minus(editorHeightMiddle)
                            )
                        },
                )

                Keyboard(state.keyboard.structure, Modifier.align(BottomCenter))
            }
        }
    }

}
