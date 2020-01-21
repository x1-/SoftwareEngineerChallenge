package ichi.x1

trait Queue[+T] {
  def isEmpty: Boolean = this eq EmptyQueue
  def enQueue[U >: T](t: U): Queue[U] = this match {
    case EmptyQueue => Cons(t, Queue.empty)
    case Cons(x, xs) => Cons(x, xs.enQueue(t))
  }
  // # Removes the element at the beginning of the immutable queue, and returns the new queue.
  def deQueue(): Queue[T] = this match {
    case EmptyQueue => throw new NoSuchElementException("This queue is empty, so cannot be dequeue.")
    case Cons(_, xs) => xs
  }
  def head: Option[T] = this match {
    case EmptyQueue => None
    case Cons(x, _) => Some(x)
  }
}
object Queue {
  def empty[T]: Queue[T] = EmptyQueue
  // Added for testing
  private[x1] def apply[T](as: T*): Queue[T] =
    if (as.isEmpty) EmptyQueue
    else Cons(as.head, apply(as.tail: _*))
}

private case class Cons[T](x: T, xs: Queue[T]) extends Queue[T]
private case object EmptyQueue extends Queue[Nothing]
