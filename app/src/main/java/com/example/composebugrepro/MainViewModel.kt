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

    fun updateState() {
        repeat(1000) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    delay(1)
                    _stateFlow.value = MainState.Loadish
                    viewModelScope.launch {
                        withContext(Dispatchers.IO) {
                            delay(3) // simulate loading
                            _stateFlow.value = MainState.Loading
                        }
                    }
                    viewModelScope.launch {
                        withContext(Dispatchers.IO) {

                            delay(17)
                            _stateFlow.value = MainState.Loadish
                        }
                    }
                    viewModelScope.launch {
                        withContext(Dispatchers.IO) {
                            delay(43) // simulate loading
                            _stateFlow.value = MainState.Loading
                        }
                    }
                }
            }
        }
        repeat(10000) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    delay(500) // simulate loading
                    _stateFlow.value = MainState.Loaded
                }
            }
        }
    }
}

sealed class MainState {
    data object Loading: MainState()
    data object Loaded: MainState()
    data object Loadish: MainState()
}
