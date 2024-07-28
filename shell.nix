{pkgs ? import <nixpkgs> {} }: pkgs.mkShell {
    nativeBuildInputs = with pkgs.buildPackages; [
        jdk22
        gradle
    ];

    env = {
        JAVA_HOME = "${pkgs.jdk22}/lib/openjdk";
    };
}
