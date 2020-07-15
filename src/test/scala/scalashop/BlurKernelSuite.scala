package scalashop

import org.junit.Test
import scalashop.image.Images

class BlurKernelSuite extends BlurSuite {
  @Test def `boxBlurKernel of a one element`: Unit = {
    val image: Img = Images.oneXOneBlackImage
    assert(boxBlurKernel(image, 0, 0, 0) == Images.blackPixel)
    assert(boxBlurKernel(image, 0, 0, 1) == Images.blackPixel)
    assert(boxBlurKernel(image, 0, 0, 2) == Images.blackPixel)
    assert(boxBlurKernel(image, 0, 0, 10) == Images.blackPixel)
  }

  @Test def `boxBlurKernel of a nine elements`: Unit = {
    val image: Img = Images.threeXThreeTestImage

    assert(boxBlurKernel(image, 1, 1, 1) == Images.grayXPixel(88))
    assert(boxBlurKernel(image, 1, 2, 1) == Images.grayXPixel(83))
    assert(boxBlurKernel(image, 2, 2, 1) == Images.grayXPixel(75))
    assert(boxBlurKernel(image, 2, 2, 2) == Images.grayXPixel(88))
  }
}
