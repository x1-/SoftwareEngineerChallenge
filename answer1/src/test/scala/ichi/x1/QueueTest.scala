package ichi.x1

import wvlet.airspec._
import wvlet.airspec.spi.PropertyCheck
import org.scalacheck.Gen

class QueueTest extends AirSpec with PropertyCheck {
  def `isEmpty of empty Queue should be true`(): Unit = {
    assert(Queue.empty.isEmpty)
  }
  def `isEmpty of nonEmpty Queue should be false`(): Unit = {
    val q = Queue.apply(1)
    assert(!q.isEmpty)
  }
  def `head of empty Queue should return None`(): Unit = {
    assert(Queue.empty.head.isEmpty)
  }
  def `head of nonEmpty Queue should return Some(the element inserted first)`(): Unit = {
    val q = Queue.apply(1, 2, 3)
    assert(q.head.contains(1))
  }
  def `enQueue of empty Queue should return new nonEmpty Queue`(): Unit = {
    assert(!Queue.empty.enQueue(10).isEmpty)
  }
  def `head of enQueued Queue should return the element inserted first`(): Unit = {
    val q = Queue.apply("grape", "apple", "orange")
    assert(q.enQueue("banana").head.contains("grape"))
  }
  def `enQueue should not change original Queue, but return new Queue`(): Unit = {
    val q = Queue.empty
    val q2 = q.enQueue("banana")
    assert(q.head.isEmpty)
    assert(q2.head.contains("banana"))
  }
  def `deQueue of empty Queue should throw NoSuchElementException`(): Unit = {
    intercept[NoSuchElementException] {
      Queue.empty.deQueue()
    }
  }
  def `deQueue of nonEmpty Queue should return new Queue without first element`(): Unit = {
    val q = Queue.apply("banana", "grape", "apple", "orange")
    assert(q.deQueue().head.contains("grape"))
  }
  def `deQueue should not change original Queue, but return new Queue`(): Unit = {
    val q = Queue.apply("banana", "grape", "apple", "orange")
    val q2 = q.deQueue()
    assert(q.head.contains("banana"))
    assert(q2.head.contains("grape"))
  }
  def `enQueue and deQueue of Queue should retain queue-order`(): Unit = {
    val q = Queue.empty
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
  def `do deQueue many times should retain valid Queue`(): Unit = {
    var q = Queue.apply(1 to 1000: _*)
    for (_ <- 1 to 999)
      q = q.deQueue()
    assert(q.head.contains(1000))
  }
  def `do enQueue many times should retain head`(): Unit = {
    val trueHead = 9999
    var q = Queue.apply(trueHead)
    forAll(Gen.choose(0, 100)){ x: Int =>
      q = q.enQueue(x)
    }
    assert(q.head.contains(trueHead))
  }
}
