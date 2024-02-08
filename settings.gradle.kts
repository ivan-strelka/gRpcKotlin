rootProject.name = "Dogs"

include("src:main:proto")
findProject(":src:main:proto")?.name = "proto"

