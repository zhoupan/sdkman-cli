package sdkman.support

import sdkman.env.SdkManBashEnvBuilder
import sdkman.stubs.CurlStub

import static sdkman.support.FilesystemUtils.prepareBaseDir

abstract class SdkmanEnvSpecification extends BashEnvSpecification {

    SdkManBashEnvBuilder sdkManBashEnvBuilder

    CurlStub curlStub

    File sdkmanBaseDirectory
    File sdkmanDotDirectory
    File candidatesDirectory

    String bootstrapScript

    def setup() {
        sdkmanBaseDirectory = prepareBaseDir()
        sdkManBashEnvBuilder = SdkManBashEnvBuilder.create(sdkmanBaseDirectory)

        sdkmanDotDirectory = new File(sdkmanBaseDirectory, ".sdkman")
        candidatesDirectory = new File(sdkmanDotDirectory, "candidates")
        bootstrapScript = "${sdkmanDotDirectory}/bin/sdkman-init.sh"
        curlStub = CurlStub.prepareIn(new File(sdkmanBaseDirectory, "bin"))
    }

    def cleanup() {
        assert sdkmanBaseDirectory.deleteDir()
    }
}
