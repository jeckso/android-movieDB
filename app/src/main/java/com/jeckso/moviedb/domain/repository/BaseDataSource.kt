package com.jeckso.moviedb.domain.repository

import com.jeckso.moviedb.data.utils.result.PendingResult
import kotlinx.coroutines.flow.Flow

interface BaseDataSource<Input, Output> {

    suspend fun create(item: Input): PendingResult<Output>

    fun retrieve(spec: RetrieveSpec): Flow<List<Output>>

    suspend fun update(spec: UpdateSpec): PendingResult<Output>

    suspend fun delete(spec: DeleteSpec): PendingResult<Output>
}