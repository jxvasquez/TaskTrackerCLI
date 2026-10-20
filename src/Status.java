public enum Status {
    TODO("Pendiente"),
    IN_PROGRESS("En Progreso"),
    DONE("Finalizada");

    private final String estado;

    Status(String estado) {
        this.estado = estado;
    }

    public String getStatus() {
        return estado;
    }
}
