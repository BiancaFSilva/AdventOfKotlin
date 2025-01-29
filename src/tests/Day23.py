import os
    
input_file_path = os.path.join(os.path.dirname(__file__), './inputs/Day23.txt')
with open(input_file_path) as file:
    input = [{conexao[:2], conexao[3:]} for conexao in file.read().split()]
    
    
def resolveParteUm(rede):
    resultado = 0
    
    for linha in range(len(rede) - 2):
        for coluna in range(linha + 1, len(rede) - 1):
            lan = rede[linha] | rede[coluna]
            if len(lan) == 3:
                for interconectaComT in range(coluna + 1, len(rede)):
                    if rede[interconectaComT].issubset(lan) and 't' in {computador[0] for computador in rede[linha] | rede[coluna] | rede[interconectaComT]}:
                        resultado += 1
                        
    return resultado
                    
                    
# Day 23: LAN Party
print("Parte 1:", resolveParteUm(input))        # 1175