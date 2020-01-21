package ichi.x1

trait Queue2[T] {
  def isEmpty: Boolean
  def enQueue(t: T): Queue2[T]
  //    # Removes the element at the beginning of the immutable queue, and returns the new queue.
  def deQueue(): Queue2[T]
  def head: Option[T]
}
case class Cons2[T](x: T, xs: Queue2[T]) extends Queue2[T] {
  override def isEmpty: Boolean = false
  override def enQueue(t: T): Queue2[T] = Cons2(x, xs.enQueue(t))
  override def deQueue(): Queue2[T] = xs
  override def head: Option[T] = Some(x)
}
class EmptyQueue2[T] extends Queue2[T] {
  override def isEmpty: Boolean = true
  override def enQueue(t: T): Queue2[T] = Cons2(t, Queue2.empty)
  override def deQueue(): Queue2[T] = throw new NoSuchElementException("This queue is empty, so cannot be dequeue.")
  override def head: Option[T] = None
}

object Queue2 {
  def empty[T]: Queue2[T] = new EmptyQueue2[T]()
  // Added for testing
  private[x1] def apply[T](as: T*): Queue[T] =
    if (as.isEmpty) EmptyQueue
    else Cons(as.head, apply(as.tail: _*))
}
