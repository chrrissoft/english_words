package com.chrrissoft.inglishwords.data.auth

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface GoogleAuthRepo {

    fun singIn(scope: CoroutineScope): Flow<AuthProviderResultState>

    fun singUp(scope: CoroutineScope): Flow<AuthProviderResultState>

}

