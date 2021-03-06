package com.gentics.mesh.dagger.module;

import javax.inject.Singleton;

import com.gentics.mesh.Mesh;
import com.gentics.mesh.etc.config.MeshOptions;
import com.gentics.mesh.search.DevNullSearchProvider;
import com.gentics.mesh.search.SearchProvider;
import com.gentics.mesh.search.TrackingSearchProvider;
import com.gentics.mesh.search.impl.ElasticSearchProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Special module which will override the search provider method in order to return a {@link TrackingSearchProvider} instead of the regular
 * {@link DevNullSearchProvider}.
 */
@Module
public class TestSearchProviderModule {

	@Provides
	@Singleton
	public static SearchProvider searchProvider(ElasticSearchProvider elasticsearchProvider) {
		MeshOptions options = Mesh.mesh().getOptions();
		SearchProvider searchProvider = null;
		// Automatically select the dummy search provider if no directory or
		// options have been specified
		if (options.getSearchOptions().getUrl() == null) {
			searchProvider = new TrackingSearchProvider();
		} else {
			searchProvider = elasticsearchProvider;
		}
		return searchProvider;
	}
}
