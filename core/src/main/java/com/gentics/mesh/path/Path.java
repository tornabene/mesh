package com.gentics.mesh.path;

import java.util.ArrayList;
import java.util.List;

/**
 * A webroot path consists of multiple segments. This class provides a useful container which can be used when resolving webroot paths.
 */
public class Path {

	private List<PathSegment> segments = new ArrayList<>();

	private String targetPath;

	/**
	 * Return the list of segments which describe the path.
	 * 
	 * @return
	 */
	public List<PathSegment> getSegments() {
		return segments;
	}

	/**
	 * Add the path segment to the path.
	 * 
	 * @param segment
	 */
	public void addSegment(PathSegment segment) {
		segments.add(segment);
	}

	/**
	 * Returns the last segment of the path.
	 * 
	 * @return Found segment or null when the path has no segments
	 */
	public PathSegment getLast() {
		if (segments.size() == 0) {
			return null;
		}
		return segments.get(segments.size() - 1);
	}

	/**
	 * Returns first segment of the path.
	 * 
	 * @return Found segment or null when the path has no segments
	 */
	public PathSegment getFirst() {
		if (segments.size() == 0) {
			return null;
		}
		return segments.get(0);
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}
}
