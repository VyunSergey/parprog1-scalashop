package scalashop

import org.junit.Test
import scalashop.image.Images
import scalashop.time.Timed

class HorizontalBlurSuite extends BlurSuite {
  @Test def `Horizontal blur of a one element`: Unit = {
    val src: Img = Images.oneXOneBlackImage
    val dst: Img = Images.oneXOneWhiteImage

    HorizontalBoxBlur.blur(src, dst, 0, 1, 1)
    assert(dst(0, 0) == Images.blackPixel)
  }

  @Test def `Horizontal parBlur of a one element`: Unit = {
    val src: Img = Images.oneXOneBlackImage
    val dst: Img = Images.oneXOneWhiteImage

    HorizontalBoxBlur.parBlur(src, dst, 1, 1)
    assert(dst(0, 0) == Images.blackPixel)
  }

  @Test def `Horizontal blur of a nine elements`: Unit = {
    val src: Img = Images.threeXThreeTestImage
    val dst: Img = Images.threeXThreeWhiteImage

    HorizontalBoxBlur.blur(src, dst, 0, 1, 1)
    assert(dst(0, 0) == Images.grayXPixel(75), "wrong update of `blur` func in dst(0, 0)")
    assert(dst(1, 0) == Images.grayXPixel(83), "wrong update of `blur` func in dst(0, 1)")
    assert(dst(2, 0) == Images.grayXPixel(75), "wrong update of `blur` func in dst(0, 2)")

    HorizontalBoxBlur.blur(src, dst, 1, 3, 1)
    assert(dst(0, 1) == Images.grayXPixel(83), "wrong update of `blur` func in dst(1, 0)")
    assert(dst(1, 1) == Images.grayXPixel(88), "wrong update of `blur` func in dst(1, 1)")
    assert(dst(2, 1) == Images.grayXPixel(83), "wrong update of `blur` func in dst(1, 2)")

    assert(dst(0, 2) == Images.grayXPixel(75), "wrong update of `blur` func in dst(2, 0)")
    assert(dst(1, 2) == Images.grayXPixel(83), "wrong update of `blur` func in dst(2, 1)")
    assert(dst(2, 2) == Images.grayXPixel(75), "wrong update of `blur` func in dst(2, 2)")
  }

  @Test def `Horizontal parBlur of a nine elements`: Unit = {
    val src: Img = Images.threeXThreeTestImage
    val dst: Img = Images.threeXThreeWhiteImage

    HorizontalBoxBlur.parBlur(src, dst, 3, 1)
    assert(dst(0, 0) == Images.grayXPixel(75), "wrong update of `blur` func in dst(0, 0)")
    assert(dst(1, 0) == Images.grayXPixel(83), "wrong update of `blur` func in dst(0, 1)")
    assert(dst(2, 0) == Images.grayXPixel(75), "wrong update of `blur` func in dst(0, 2)")

    assert(dst(0, 1) == Images.grayXPixel(83), "wrong update of `blur` func in dst(1, 0)")
    assert(dst(1, 1) == Images.grayXPixel(88), "wrong update of `blur` func in dst(1, 1)")
    assert(dst(2, 1) == Images.grayXPixel(83), "wrong update of `blur` func in dst(1, 2)")

    assert(dst(0, 2) == Images.grayXPixel(75), "wrong update of `blur` func in dst(2, 0)")
    assert(dst(1, 2) == Images.grayXPixel(83), "wrong update of `blur` func in dst(2, 1)")
    assert(dst(2, 2) == Images.grayXPixel(75), "wrong update of `blur` func in dst(2, 2)")
  }

  @Test def `Horizontal blur & parBlur of a 1920 * 1080 elements`: Unit = {
    val radius = 3
    val width = 1920
    val height = 1080
    val numTasks = 128

    val src: Img = Images.nXmBlackImage(width, height)
    val dstSeq: Img = Images.nXmWhiteImage(width, height)
    val dstPar: Img = Images.nXmWhiteImage(width, height)

    Timed[Unit].timed(HorizontalBoxBlur.blur(src, dstSeq, 0, height, radius), "Horizontal blur")
    Timed[Unit].timed(HorizontalBoxBlur.parBlur(src, dstPar, numTasks, radius), "Horizontal parBlur")

    val eqv =
      (for {
        x <- 0 until width
        y <- 0 until height
      } yield dstSeq(x, y) == dstPar(x, y))
        .reduce(_ && _)

    assert(eqv, "wrong update of `blur` and `parBlur` func`s")
  }
}
