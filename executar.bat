@echo off
echo ========================================
echo Sistema de Gerenciamento de Franquias
echo ========================================
echo.
echo Compilando projeto...
call mvn clean compile
if %errorlevel% neq 0 (
    echo ERRO: Falha na compilacao
    pause
    exit /b 1
)
echo.
echo Projeto compilado com sucesso!
echo Iniciando aplicacao...
echo.
java -cp target/classes com.mycompany.trabalho03oo.Main
pause
