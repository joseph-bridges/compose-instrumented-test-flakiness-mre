/*
 * Copyright 2024 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.composebugrepro

import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.composebugrepro.MainState.Loaded
import com.example.composebugrepro.MainState.Loading
import com.example.composebugrepro.ui.theme.ComposeBugReproTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<MainViewModel>()

        setContent {
            ComposeBugReproTheme {
                val state by viewModel.stateFlow.collectAsState()
                Log.i("FLAKE", "Updating UI with state ${state}. Is on main thread?: ${Looper.getMainLooper().isCurrentThread}. Thread name: ${Thread.currentThread().name}")
                when(state) {
                    is Loading -> Loading()
                    is Loaded -> Loaded()
                    MainState.Loadish -> Loadish()
                }
            }
            LaunchedEffect(Unit) {
                viewModel.updateState()
            }
        }
    }
}

@Composable
private fun Loading() {
    Scaffold {
        Text(modifier = Modifier.padding(it), text = "Content loading")
    }
}

@Composable
private fun Loadish() {
    Scaffold {
        Text(modifier = Modifier.padding(it), text = "Content loadish")
    }
}

@Composable
private fun Loaded() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My App") },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Text(modifier = Modifier.padding(it), text = "Content loaded")
    }
}
