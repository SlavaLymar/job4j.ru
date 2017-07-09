package ru.yalymar.generic.store;

/**
 * @author slavalymar
 * @since 06.03.2017
 * @version 1
 * @param <T>
 */
public abstract class AbstractStore <T> implements Store{

    /**
     * class that contains storages
     */
    protected SimpleArray<T> values;

    public SimpleArray<T> getValues() {
        return this.values;
    }

    /** add value to container
     * @param value
     */
    public void add(T value){
        this.values.add(value);
    }

    /** update store in container
     * @param value
     * @param newValue
     * @return boolean
     * @throws IncorrectIdException
     */
    public boolean update(T value, T newValue) throws IncorrectIdException {
        boolean result = false;
        for(int i = 0; i<this.values.getArr().length; i++){
            if(this.values.getArr()[i] != null && this.values.getArr()[i].equals(value)) {
                this.values.update(i, newValue);
                result = true;
                break;
            }
        }
        if(result) return result;
        else throw new IncorrectIdException("This Id wasn`t found!");
    }

    /** delete store in container
     * @param value
     * @return boolean
     * @throws IncorrectIdException
     */
    public boolean delete(T value) throws IncorrectIdException {
        boolean result = false;
        for(int i = 0; i<this.values.getArr().length; i++){
            if(this.values.getArr()[i] != null && this.values.getArr()[i].equals(value)) {
                this.values.delete(i);
                result = true;
                break;
            }
        }
        if(result) return result;
        else throw new IncorrectIdException("This Id wasn`t found!");
    }

}
