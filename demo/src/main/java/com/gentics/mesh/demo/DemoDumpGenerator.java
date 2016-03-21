package com.gentics.mesh.demo;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gentics.mesh.changelog.ChangelogSystem;
import com.gentics.mesh.cli.BootstrapInitializer;
import com.gentics.mesh.graphdb.spi.Database;

public class DemoDumpGenerator {

	static {
		DemoDumpConfiguration.enabled = true;
	}

	public static void main(String[] args) throws Exception {
		new DemoDumpGenerator().dump();
	}

	private void dump() throws Exception {
		// Cleanup in preparation for dumping the demo data
		FileUtils.deleteDirectory(new File("target" + File.separator + "dump"));
		File confFile = new File("mesh.json");
		if (confFile.exists()) {
			confFile.delete();
		}
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DemoDumpConfiguration.class)) {
			ctx.start();
			ctx.registerShutdownHook();
			// Initialize mesh
			BootstrapInitializer boot = ctx.getBean(BootstrapInitializer.class);
			boot.initSearchIndex();
			boot.initMandatoryData();
			boot.initPermissions();

			Database db = ctx.getBean(Database.class);
			ChangelogSystem cls = new ChangelogSystem(db);
			cls.markAllAsApplied();

			// Setup demo data
			DemoDataProvider provider = ctx.getBean("demoDataProvider", DemoDataProvider.class);
			provider.setup();
			System.exit(0);
		}

	}

}