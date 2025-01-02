{pkgs ? import <nixpkgs> {} }: pkgs.mkShell {
    nativeBuildInputs = with pkgs.buildPackages; [
        jdk
        gradle
    ];

    env = {
        JAVA_HOME = "${pkgs.jdk}/lib/openjdk";
    };
}
