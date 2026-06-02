package model.Enums;

public enum TipoCadastro {
    ERRO,
    ADMIN,
    USER;

    public static TipoCadastro tipoCadastroInt(int entrada) {
      if(entrada < 0 || entrada >= values().length) {
        return ERRO;
      } else {
        return values()[entrada];
      }
    }
}
