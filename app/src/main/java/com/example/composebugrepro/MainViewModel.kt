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

package com.example.composebugrepro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    private val _stateFlow = MutableStateFlow<MainState>(MainState.Loading)
    val stateFlow: StateFlow<MainState> = _stateFlow

    suspend fun launchUpdates(count: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                delay(31)
                _stateFlow.value = MainState.Loadish
                delay(33)
                _stateFlow.value = MainState.Loading
                delay(37)
                _stateFlow.value = MainState.Loadish
                delay(43)
                _stateFlow.value = MainState.Loading
                if (count > 0) launchUpdates(count - 7)
            }
        }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                delay(31)
                _stateFlow.value = MainState.Loading
                delay(33)
                _stateFlow.value = MainState.Loadish
                delay(37)
                _stateFlow.value = MainState.Loading
                delay(43)
                _stateFlow.value = MainState.Loadish
                if (count > 0) launchUpdates(count - 11)
            }
        }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                delay(1200)
                _stateFlow.value = MainState.Loaded
                if (count > 0) launchUpdates(count - 1)
            }
        }
    }

    suspend fun updateState() {
        launchUpdates(30)
    }
}

sealed class MainState {
    data object Loading: MainState()
    data object Loaded: MainState()
    data object Loadish: MainState()
}
