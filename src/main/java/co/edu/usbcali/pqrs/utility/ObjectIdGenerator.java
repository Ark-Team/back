package co.edu.usbcali.pqrs.utility;

import org.bson.types.ObjectId;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class ObjectIdGenerator implements IdentifierGenerator {

  @Override
  public Serializable generate(
      SharedSessionContractImplementor sharedSessionContractImplementor, Object o)
      throws HibernateException {
    return ObjectId.get().toHexString();
  }
}
