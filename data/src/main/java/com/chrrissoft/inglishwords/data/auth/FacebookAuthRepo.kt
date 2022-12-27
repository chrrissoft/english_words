package com.chrrissoft.inglishwords.data.auth

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface FacebookAuthRepo {

    fun auth(scope: CoroutineScope): Flow<AuthProviderResultState>

}
