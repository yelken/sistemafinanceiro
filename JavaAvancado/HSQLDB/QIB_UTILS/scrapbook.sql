delete from tb_cliente where NOME like '>%'
SELECT * FROM tb_cliente inner join Tb_endereco on cpf=tb_cliente_cpf order by nome