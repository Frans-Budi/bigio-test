package com.fransbudikashira.core.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fransbudikashira.core.data.source.remote.network.ApiService
import com.fransbudikashira.core.domain.model.Episode
import com.fransbudikashira.core.util.DataMapper

class EpisodePagingSource (
    private val apiService: ApiService
): PagingSource<Int, Episode>() {

    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getAllEpisode(page)
            val episodes = DataMapper.mapEpisodeResultToEpisodeDomain(response.results)

            LoadResult.Page(
                data = episodes,
                prevKey = null,
                nextKey = if(response.results.isNotEmpty()) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}