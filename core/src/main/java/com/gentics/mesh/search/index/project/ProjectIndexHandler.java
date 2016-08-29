package com.gentics.mesh.search.index.project;

import java.util.Collections;
import java.util.Set;

import javax.inject.Inject;

import com.gentics.mesh.context.InternalActionContext;
import com.gentics.mesh.core.data.Project;
import com.gentics.mesh.core.data.root.RootVertex;
import com.gentics.mesh.core.data.search.SearchQueueEntry;
import com.gentics.mesh.dagger.MeshCore;
import com.gentics.mesh.graphdb.spi.Database;
import com.gentics.mesh.search.SearchProvider;
import com.gentics.mesh.search.index.AbstractIndexHandler;

public class ProjectIndexHandler extends AbstractIndexHandler<Project> {

	private final static Set<String> indices = Collections.singleton(Project.TYPE);

	private ProjectTransformator transformator = new ProjectTransformator();

	@Inject
	public ProjectIndexHandler(SearchProvider searchProvider, Database db) {
		super(searchProvider, db);
	}

	public ProjectTransformator getTransformator() {
		return transformator;
	}

	@Override
	protected String getIndex(SearchQueueEntry entry) {
		return Project.TYPE;
	}

	@Override
	public Set<String> getIndices() {
		return indices;
	}

	@Override
	public Set<String> getAffectedIndices(InternalActionContext ac) {
		return indices;
	}

	@Override
	protected String getType() {
		return Project.TYPE;
	}

	@Override
	public String getKey() {
		return Project.TYPE;
	}

	@Override
	protected RootVertex<Project> getRootVertex() {
		return MeshCore.get().boot().meshRoot().getProjectRoot();
	}

}