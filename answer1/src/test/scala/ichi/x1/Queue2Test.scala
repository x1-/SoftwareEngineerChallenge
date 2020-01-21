package ichi.x1

import wvlet.airspec._
import wvlet.airspec.spi.PropertyCheck
import org.scalacheck.Gen

class Queue2Test extends AirSpec with PropertyCheck {
  def `isEmpty of empty Queue2 should be true`(): Unit = {
    assert(Queue2.empty.isEmpty)
  }
  def `isEmpty of nonEmpty Queue2 should be false`(): Unit = {
    val q = Queue2.apply(1)
    assert(!q.isEmpty)
  }
  def `head of empty Queue2 should return None`(): Unit = {
    assert(Queue2.empty.head.isEmpty)
  }
  def `head of nonEmpty Queue2 should return Some(the element inserted first)`(): Unit = {
    val q = Queue2.apply(1, 2, 3)
    assert(q.head.contains(1))
  }
  def `enQueue of empty Queue2 should return new nonEmpty Queue2`(): Unit = {
    assert(!Queue2.empty.enQueue(10).isEmpty)
  }
  def `head of enQueued Queue2 should return the element inserted first`(): Unit = {
    val q = Queue2.apply("grape", "apple", "orange")
    assert(q.enQueue("banana").head.contains("grape"))
  }
  def `enQueue should not change original Queue2, but return new Queue2`(): Unit = {
    val q = Queue2.empty[String]
    val q2 = q.enQueue("banana")
    assert(q.head.isEmpty)
    assert(q2.head.contains("banana"))
  }
  def `deQueue of empty Queue2 should throw NoSuchElementException`(): Unit = {
    intercept[NoSuchElementException] {
      Queue2.empty.deQueue()
    }
  }
  def `deQueue of nonEmpty Queue2 should return new Queue2 without first element`(): Unit = {
    val q = Queue2.apply("banana", "grape", "apple", "orange")
    assert(q.deQueue().head.contains("grape"))
  }
  def `deQueue should not change original Queue2, but return new Queue2`(): Unit = {
    val q = Queue2.apply("banana", "grape", "apple", "orange")
    val q2 = q.deQueue()
    assert(q.head.contains("banana"))
    assert(q2.head.contains("grape"))
  }
  def `enQueue and deQueue of Queue2 should retain queue-order`(): Unit = {
    val q = Queue2.empty
      .enQueue("1.banana")
      .enQueue("2.grape")
      .enQueue("3.apple")

    assert(q.head.contains("1.banana"))

    val q2 = q.deQueue()
    assert(q2.head.contains("2.grape"))

    val q3 = q2.deQueue()
    assert(q3.head.contains("3.apple"))

    val q4 = q3.deQueue()
    assert(q4.isEmpty)
  }
  def `do deQueue many times should retain valid Queue2`(): Unit = {
    var q = Queue2.apply(1 to 1000: _*)
    for (_ <- 1 to 999)
      q = q.deQueue()
    assert(q.head.contains(1000))
  }
  def `do enQueue many times should retain head`(): Unit = {
    val trueHead = 9999
    var q = Queue2.apply(trueHead)
    forAll(Gen.choose(0, 100)){ x: Int =>
      q = q.enQueue(x)
    }
    assert(q.head.contains(trueHead))
  }
}
