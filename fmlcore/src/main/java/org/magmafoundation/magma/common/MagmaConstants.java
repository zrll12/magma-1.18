package org.magmafoundation.magma.common;

import java.util.Objects;

public class MagmaConstants {
    private static final String fullVersion = (MagmaConstants.class.getPackage().getImplementationVersion() != null) ? MagmaConstants.class.getPackage().getImplementationVersion() : "dev-env";

    public static final String NAME = "Magma";
    public static final String BRAND = "MagmaFoundation";
    public static final String VERSION = !Objects.equals(fullVersion, "dev-env") ? fullVersion.split("-")[0] + "-" + fullVersion.split("-")[2] : "dev-env";
    public static final String BUKKIT_VERSION = "v1_18_R2";
    public static final String FORGE_VERSION_FULL = fullVersion;
    public static final String FORGE_VERSION = fullVersion.substring(0, fullVersion.lastIndexOf("-"));
    public static final String NMS_PREFIX = "net/minecraft/server/";
    public static final String INSTALLER_LIBRARIES_FOLDER = "libraries";
}
