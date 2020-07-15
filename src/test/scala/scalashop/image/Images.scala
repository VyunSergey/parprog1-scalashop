package scalashop.image

import scalashop.{Img, RGBA, rgba}

object Images {
  def oneXOnePixelImage(pixel: RGBA): Img =
    new Img(1, 1, Array(pixel))

  def threeXThreePixelImage(pixels: List[RGBA]): Img =
    new Img(3, 3, Array(pixels.take(9): _*))

  def nXmPixelImage(n: Int, m: Int, pixels: List[RGBA]): Img =
    new Img(n, m, Array(pixels.take(n * m): _*))

  val blackPixel: RGBA = rgba(0, 0, 0, 0)
  val whitePixel: RGBA = rgba(255, 255, 255, 255)

  def grayXPixel(x: Int): RGBA = rgba(x, x, x, x)
  val grayPixel: RGBA = grayXPixel(100)

  val oneXOneBlackImage: Img = oneXOnePixelImage(blackPixel)
  val oneXOneGrayImage: Img = oneXOnePixelImage(grayPixel)
  val oneXOneWhiteImage: Img = oneXOnePixelImage(whitePixel)

  val threeXThreeBlackImage: Img = threeXThreePixelImage(List(
    blackPixel, blackPixel, blackPixel,
    blackPixel, blackPixel, blackPixel,
    blackPixel, blackPixel, blackPixel
  ))

  val threeXThreeTestImage: Img = threeXThreePixelImage(List(
    grayPixel, grayPixel, grayPixel,
    grayPixel, blackPixel, grayPixel,
    grayPixel, grayPixel, grayPixel
  ))

  val threeXThreeWhiteImage: Img = threeXThreePixelImage(List(
    whitePixel, whitePixel, whitePixel,
    whitePixel, whitePixel, whitePixel,
    whitePixel, whitePixel, whitePixel
  ))

  def nXmBlackImage(n: Int, m: Int): Img =
    nXmPixelImage(n, m, List.range(0, n * m).map(_ => blackPixel))

  def nXmWhiteImage(n: Int, m: Int): Img =
    nXmPixelImage(n, m, List.range(0, n * m).map(_ => whitePixel))
}
