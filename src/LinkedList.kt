class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun mergeTwoListsRecursive(list1: ListNode?, list2: ListNode?): ListNode? {
    if(list1 == null) return list2
    if(list2 == null) return list1

    if(list1.`val` < list2.`val`){
        list1.next = mergeTwoListsRecursive(list1.next, list2)
        return list1
    } else {
        list2.next = mergeTwoListsRecursive(list1, list2.next)
        return list2
    }
}
fun mergeTwoListsIterative(list1: ListNode?, list2: ListNode?): ListNode? {
    if(list1 == null) return list2
    if(list2 == null) return list1

    var l1 = list1
    var l2 = list2
    var head = l1


    if(l1.`val` > l2.`val`){
        head = l2
        l2 = l2.next
    } else {
        l1 = l1.next
    }

    var tail = head
    while (l1 != null && l2 != null){
        if(l1.`val` < l2.`val`){
            tail?.next = l1
            l1 = l1.next
        } else {
            tail?.next = l2
            l2 = l2.next
        }
        tail = tail?.next
    }

    if(l1 != null){
        tail?.next = l1
    } else {
        tail?.next = l2
    }
    return head
}

fun reverseListIterative(head: ListNode?): ListNode? {
    if(head == null) return head
    var curr = head
    var prev: ListNode? = null
    var next = head.next

    while (next != null){
        curr?.next = prev
        prev = curr
        curr = next
        next = next.next
    }
    curr?.next = prev
    return curr
}

fun reverseListRecursive(head: ListNode?): ListNode? {
    if(head == null || head.next == null) return head
    val newHead = reverseListRecursive(head.next)

    head.next!!.next = head
    head.next = null
    return newHead
}

fun deleteDuplicates(head: ListNode?): ListNode? {
    var curr = head

    while(curr != null && curr.next != null){
        var next = curr.next
        while(next != null && curr.`val` == next.`val`){
            next = next.next
        }
        curr.next = next
    }
    return head
}

fun deleteDuplicates2(head: ListNode?): ListNode? {
    if(head == null || head.next == null) return head
    val fake = ListNode(-1)
    fake.next = head
    var prev: ListNode? = fake
    var curr = head

    while(curr!== null){
        while(curr!!.next !=null && curr.`val` == curr.next!!.`val`){
            curr = curr.next
        }
        if(prev?.next == curr){
            prev = prev.next
        } else {
            prev?.next = curr.next
        }
        curr = curr.next
    }
    return fake.next
}

fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
    if(head == null || head.next == null || left == right) return head
    var length = 1
    var dummy = head
    var curr = dummy
    var l: ListNode? = null
    while (length < left){
        l = curr
        curr = curr?.next
        length++
    }


    var r = curr
    var prev: ListNode? = null
    var next = curr?.next
    while (length <= right){
        curr?.next = prev
        prev = curr
        curr = next
        next = next?.next
        length++
    }

    if(l == null) {
        dummy = prev
    } else {
        l.next = prev
    }

    r?.next = curr
    return dummy
}

fun printList(head: ListNode?){
    var curr = head
    while (curr != null){
        print("${curr.`val`} -> ")
        curr = curr.next
    }
    println("null")
}

fun main() {
    val head = ListNode(1)
    head.next = ListNode(2)
    head.next?.next = ListNode(3)
    head.next?.next?.next = ListNode(4)
    head.next?.next?.next?.next = ListNode(5)
    val h1 = reverseBetween(head, 1, 4)
    printList(h1)
}