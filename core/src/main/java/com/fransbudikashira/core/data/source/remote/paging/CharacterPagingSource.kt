package com.fransbudikashira.core.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fransbudikashira.core.data.source.remote.network.ApiService
import com.fransbudikashira.core.domain.model.Character
import com.fransbudikashira.core.util.DataMapper

class CharacterPagingSource (
    private val apiService: ApiService
): PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getAllCharacter(page)
            val characters = DataMapper.mapCharacterResultToCharacterDomain(response.results)

            LoadResult.Page(
                data = characters,
                prevKey = null,
                nextKey = if(response.results.isNotEmpty()) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}