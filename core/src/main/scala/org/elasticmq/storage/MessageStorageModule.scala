package org.elasticmq.storage

import org.elasticmq._

trait MessageStorageModule {
  trait MessageStorage {
    def persistMessage(message: SpecifiedMessage)
    def updateMessage(message: SpecifiedMessage)

    /**
     * Tries to update the given message with a new next delivery value. Will return the updated message if
     * the update succeedes, {@code None} otherwise. For the update to succeed, a message with the same id
     * and next delivery value must be found as in {@code message}; hence, next delivery is in fact an
     * optimistic lock.
     */
    def updateNextDelivery(message: SpecifiedMessage, nextDelivery: MillisNextDelivery): Option[SpecifiedMessage]
    def deleteMessage(message: IdentifiableMessage)

    def lookupMessage(id: String): Option[SpecifiedMessage]
    def lookupPendingMessage(queue: Queue, deliveryTime: Long): Option[SpecifiedMessage]
  }

  def messageStorage: MessageStorage
}